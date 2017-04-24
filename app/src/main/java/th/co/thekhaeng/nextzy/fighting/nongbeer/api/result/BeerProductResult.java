package th.co.thekhaeng.nextzy.fighting.nongbeer.api.result;

/**
 * Created by thekhaeng on 3/20/2017 AD.
 */

public class BeerProductResult{
    /**
     * id : 0102023
     * name : Hoegaarden
     * price : 300
     * volume : 300ml
     * alcohol : Alc 7.2% vol
     * image : www.photobucket.com/s1043/adsacviosdfwel.jpg
     */

    private String id;
    private String name;
    private int price;
    private String volume;
    private String alcohol;
    private String image;
    private int amount;

    public String getId(){
        return id;
    }

    public void setId( String id ){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice( int price ){
        this.price = price;
    }

    public String getVolume(){
        return volume;
    }

    public void setVolume( String volume ){
        this.volume = volume;
    }

    public String getAlcohol(){
        return alcohol;
    }

    public void setAlcohol( String alcohol ){
        this.alcohol = alcohol;
    }

    public String getImage(){
        return image;
    }

    public void setImage( String image ){
        this.image = image;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount( int amount ){
        this.amount = amount;
    }

    public BeerProductResult(){
    }

}
