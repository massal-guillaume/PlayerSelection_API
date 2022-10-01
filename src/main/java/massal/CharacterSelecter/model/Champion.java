package massal.CharacterSelecter.model;


import javax.persistence.*;

@Embeddable
public class Champion {


    private String name;

    public Champion() {
    }

    public Champion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
