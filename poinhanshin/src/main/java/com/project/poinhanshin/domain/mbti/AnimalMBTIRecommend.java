package com.project.poinhanshin.domain.mbti;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalMBTIRecommend {
    private integer MBTI;   // FK
    private integer animalno;   // PK
    private varchar animalname;
}
