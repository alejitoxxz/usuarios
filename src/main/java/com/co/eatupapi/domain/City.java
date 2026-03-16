package com.co.eatupapi.domain;

import java.util.UUID;

public record City(UUID id, UUID departmentId, String name) {
}
