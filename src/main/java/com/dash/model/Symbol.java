package com.dash.model;

import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Symbol(String value) {}
