package com.desafios_backend.auth;

public final class RegexPatterns {
    private RegexPatterns() {}

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[^A-Za-z0-9]).+$";
}
