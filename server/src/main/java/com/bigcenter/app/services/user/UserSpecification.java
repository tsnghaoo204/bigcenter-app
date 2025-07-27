package com.bigcenter.app.services.user;

import org.springframework.data.jpa.domain.Specification;
import com.bigcenter.app.entities.User; // hoặc Teacher, tùy entity bạn đang dùng

public class UserSpecification {
    public static Specification<User> hasFullName(String fullName) {
        return (root, query, cb) ->
                fullName == null ? null : cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%");
    }

    public static Specification<User> hasPhone(String phone) {
        return (root, query, cb) ->
                phone == null ? null : cb.like(cb.lower(root.get("phone")), "%" + phone.toLowerCase() + "%");
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, cb) ->
                email == null ? null : cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }
}
