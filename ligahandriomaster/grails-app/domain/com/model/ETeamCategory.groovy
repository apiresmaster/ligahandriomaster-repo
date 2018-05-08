package com.model

enum ETeamCategory {
    TRINTAECINCOMAIS("Trinta e Cinco+", 35),
    QUARENTAEDOIS("Quarenta e Dois+", 42),
    CINQUENTA("Cinquenta+", 50)

    private final String description
    private final int limitAge

    ETeamCategory(String description, int limitAge){
        this.description = description
        this.limitAge = limitAge
    }

    String getDescription(){
        description
    }

    int getLimitAge(){
        limitAge
    }
}