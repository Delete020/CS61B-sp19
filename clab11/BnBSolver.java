import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    List<Bear> solvedBears;
    List<Bed> solvedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        solvedBears = new ArrayList<>();
        solvedBeds = new ArrayList<>();
        quickSort(bears, beds);
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return solvedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return solvedBeds;
    }

    private void quickSort(List<Bear> bears, List<Bed> beds) {
        if (bears.isEmpty()) {
            return;
        }

        Bear bearPivot = getRandomBear(bears);
        List<Bed> lessBed = new ArrayList<>();
        List<Bed> equalBed = new ArrayList<>();
        List<Bed> greaterBed = new ArrayList<>();
        quickSortBed(beds, bearPivot, lessBed, equalBed, greaterBed);

        Bed bedPivot = equalBed.get(0);
        List<Bear> lessBear = new ArrayList<>();
        List<Bear> equalBear = new ArrayList<>();
        List<Bear> greaterBear = new ArrayList<>();
        quickSortBear(bears, bedPivot, lessBear, equalBear, greaterBear);

        solvedBeds.addAll(equalBed);
        solvedBears.addAll(equalBear);

        quickSort(lessBear, lessBed);
        quickSort(greaterBear, greaterBed);
    }

    private Bear getRandomBear(List<Bear> list) {
        return list.get((int) (Math.random() * list.size()));
    }

    private void quickSortBed(List<Bed> beds, Bear pivot, List<Bed> less,
                              List<Bed> equal, List<Bed> greater) {
        for (Bed bed : beds) {
            if (bed.compareTo(pivot) > 0) {
                greater.add(bed);
            } else if (bed.compareTo(pivot) < 0) {
                less.add(bed);
            } else {
                equal.add(bed);
            }
        }
    }

    private void quickSortBear(List<Bear> bears, Bed pivot, List<Bear> less,
                               List<Bear> equal, List<Bear> greater) {
        for (Bear bear : bears) {
            if (bear.compareTo(pivot) > 0) {
                greater.add(bear);
            } else if (bear.compareTo(pivot) < 0) {
                less.add(bear);
            } else {
                equal.add(bear);
            }
        }
    }

}