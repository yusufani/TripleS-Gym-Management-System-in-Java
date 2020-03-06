package sample;

public class Hareket {
    private String pazartesi;
    private String sali;
    private String carsamba;
    private String persembe;
    private String cuma;
    private String cumartesi;
    private String pazar;


    public Hareket(String pazartesi, String sali, String carsamba, String persembe, String cuma, String cumartesi, String pazar) {
        this.pazartesi = pazartesi;
        this.sali = sali;
        this.carsamba = carsamba;
        this.persembe = persembe;
        this.cuma = cuma;
        this.cumartesi = cumartesi;
        this.pazar = pazar;
    }

    public String getPazartesi() {
        return pazartesi;
    }

    public String getSali() {
        return sali;
    }

    public String getCarsamba() {
        return carsamba;
    }

    public String getPersembe() {
        return persembe;
    }

    public String getCuma() {
        return cuma;
    }

    public String getCumartesi() {
        return cumartesi;
    }

    public String getPazar() {
        return pazar;
    }
}
