package com.dash.model;

import io.micronaut.serde.annotation.SerdeImport;

@SerdeImport
public record Symbol(String value) {}
