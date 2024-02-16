import java.util.*;
//compare id values of verses and sort accordingly in further additions
class idComparator implements Comparator<Verse> {
    @Override
    public int compare(Verse v1, Verse v2) {
        return Integer.compare(v1.getId(), v2.getId());
    }
}