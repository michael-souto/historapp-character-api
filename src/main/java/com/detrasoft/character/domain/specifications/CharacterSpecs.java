package com.detrasoft.character.domain.specifications;

import com.detrasoft.character.domain.entities.Character;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class CharacterSpecs {

    public static Specification<Character> byName(String name) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (name != null && !name.isBlank()) {
                predicates.add(
                        builder.like(
                                builder.upper(root.get("name").as(String.class)),
                                "%" + name.toUpperCase() + "%"));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
