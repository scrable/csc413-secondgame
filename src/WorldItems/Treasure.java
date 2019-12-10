package src.WorldItems;

public abstract class Treasure extends WorldItem{
    private int treasureValue;

    public int getTreasureValue() {
        return treasureValue;
    }

    public void setTreasureValue(int treasureValue) {
        this.treasureValue = treasureValue;
    }
}
