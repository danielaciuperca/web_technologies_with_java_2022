package com.unibuc.recap.dto;

public class UpdateDriverDto extends CreateDriverDto {
    
    private long id;

    public UpdateDriverDto(long id) {
        this.id = id;
    }

    public UpdateDriverDto(String name, String email, String city, long id) {
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
