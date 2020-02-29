package ru.mail.ilya6697089.model;

public class ItemDTO {

    private String name;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
