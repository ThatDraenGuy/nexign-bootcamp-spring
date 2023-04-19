package com.draen.service.client.generator;

import com.draen.domain.entity.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientGenerator {
    Client generateClient();
}
