package com.workoutnow.analytics.service.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt

object UserUtil {
    val currentUserId: String
        get() {
            val jwt = SecurityContextHolder.getContext().authentication.principal as Jwt
            return jwt.claims["sub"].toString()
        }
}