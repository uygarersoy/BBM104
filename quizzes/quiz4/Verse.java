//implements comparable to support sorting mechanism to treeset
public class Verse implements Comparable<Verse>{
    private int id;
    private String text;

    public Verse(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    //uses lexicographically sorting to sort verses according to string value of their ids
    @Override
    public int compareTo(Verse other) {
        return String.valueOf(this.id).compareTo(String.valueOf(other.id));
    }
}