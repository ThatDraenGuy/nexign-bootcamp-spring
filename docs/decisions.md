# Принятые решения и структура проекта
## Общие моменты
**Стэк технологий**
- JDK 17
- Gradle
- Spring, Spring Boot
- Postgresql
- Apache ActiveMQ Artemis

## Модули и микросервисы
### common
Модуль, используемый как зависимость в других модулях. 
Содержит стандартные интерфейсы, сущности, репозитории, сервисы и т.д.

Не является отдельным микросервисом.

## server-config
Отдельный микросервис, использующий `spring cloud config server` 
для централизованной конфигурации. Остальные сервисы подключаются к нему
и получают конфиг.

## data-gen
Микросервис, занимающийся генерацией данных (cdr и клиентов) и популяцией БД.

Я решил рассматривать этот модуль как модуль для дебаггинга, поэтому 
вынес в него управление сбросом и наполнением БД. 
Микросервис предоставляет свои эндпоинты для генерации и сброса БД.

Похвастаться крутыми алгоритмами генерации или осмысленной рандомизацией модуль не может.
В рамках ограниченного времени генерация была реализована "в лоб", но всё равно поддерживает конфигурацию
(число записей в cdr, число клиентов, границы баланса, длины звонка и прочее)

Эта конфигурация держится отдельно от централизованной (т.к. нужна только одному модулю)
и находится [здесь](../data-gen/src/main/resources/application.properties)

## gateway
Микросервис, представляющий общий эндпоинт для рест-сервисов (`data-gen` и `crm`).
Кроме этого, в этом сервисе настроена авторизация. Конечно, в "идельном" случае 
Security и Gateway выносятся в отдельные микросервисы, но т.к. проект небольшой
я ограничился одним модулем под оба сервиса.

Gateway перенаправляет запросы на `crm` и `data-gen` соответственно 
(либо обрабатывает запрос сам, Если он связан с авторизацией)

Для авторизации я решил использовать jwt-токены, реализованные "ручками".
По сути сделано это было просто для примера.

В рамках сущностей и таблиц в БД я решил разделить клиентов (обладающих номерами телефонов и совершающих звонки)
и пользователей (обладающих возможностью доступа к рест-сервису).
Это обосновано необходимостью суещствования менеджеров, которые обладают 
доступом к рест-сервисам, но не учавствуют в процессе тарификации как клиенты.

## crm
Микросервис, предоставляющий рест-сервис для абонентов и менеджеров.

Для связи микросервисов для выполнения тарификации используется Apache ActiveMQ Artemis

## brt
Микросервис, занимающийся обработкой cdr-файлов и балансом клиентов.

Я рассматривал процесс тарификации как нагруженный процесс, поэтому старался 
ориентироваться на наименьшее число "лишних" операций в процессе.
Это проявляется, например, в том, что классы, представляющие записи в cdr и cdr+
используют в качестве полей строки, для избежания лишнего маппинга.

Этот момент несколько спорный, т.к. хотяи рассуждаю на тему нагруженности и оптимизации,
у меня не дошли руки до кэширования данных, из-за чего все операции
совершают "затратные" обращения к БД. 

Если бы хватило времени, то я скорее всего реализовал бы инвалидацию кэша, 
используя PgSubscriber из `reactive-pg-client` для нотификаций изменений в БД


## hrs
Микросервис, занимающийся расчётом стоимости звонков по тарифам.

Основной задачей в реализации данного модуля было создание расширяемой
системы тарифов.

В идеале нужно было бы создать некоторую вычислительную систему, способную 
реализовывать логику тарифа как набор команд, использующих 
широкий набор параметров, что позволяло бы хранить логику тарифов вне java-кода.

Конечно, в рамках этого проекта подобную систему не реализовать.
Поэтому, для достижения по крайней мере удобной расширяемости,
логика тарифа инкапсулируется в бин, реализующий интерфейс `TariffLogicService`,
а в БД хранится свзяь кода тарифа и названия этого бина.

Разумеется, такой подход вынуждает при запуске `hrs` валидировать, что все
бины, специализированные в БД, присутствуют в приложении.




