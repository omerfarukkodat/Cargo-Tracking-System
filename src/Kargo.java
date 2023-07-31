class Kargo implements Comparable<Kargo> {
    private String takipNumarasi;
    private String ad;
    private String adres;
    private String cikisIl;
    private String varisIl;
    private String durum;

    public Kargo(String takipNumarasi, String ad, String adres, String cikisIl, String varisIl, String durum) {
        this.takipNumarasi = takipNumarasi;
        this.ad = ad;
        this.adres = adres;
        this.cikisIl = cikisIl;
        this.varisIl = varisIl;
        this.durum = durum;
    }

    public String getTakipNumarasi() {
        return takipNumarasi;
    }

    public String getAd() {
        return ad;
    }

    public String getAdres() {
        return adres;
    }

    public String getCikisIl() {
        return cikisIl;
    }

    public String getVarisIl() {
        return varisIl;
    }

    public String getDurum() {
        return durum;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    @Override
    public int compareTo(Kargo other) {
        return this.takipNumarasi.compareTo(other.getTakipNumarasi());
    }

    @Override
    public String toString() {
        return "Takip Numarası: " + takipNumarasi +
                "\nAd: " + ad +
                "\nAdres: " + adres +
                "\nÇıkış İli: " + cikisIl +
                "\nVarış İli: " + varisIl +
                "\nDurum: " + durum;
    }
}
