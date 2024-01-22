package com.unibuc.recap.dto;

public class UpdateDriverRequestDto extends CreateDriverRequestDto {
    
    private long id;

    public UpdateDriverRequestDto(long id) {
        this.id = id;
    }

    public UpdateDriverRequestDto(String name, String email, String city, long id) {
        super(name, email, city);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
