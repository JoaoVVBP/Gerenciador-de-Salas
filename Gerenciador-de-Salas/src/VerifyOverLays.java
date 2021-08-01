public class VerifyOverLays{
    public static Guest verifyOverLays(Guest guest1, Guest guest2) {
        Guest guest3 = new Guest();
        int i = 0; 
        while (i < guest1.listDate.size()) {
            int j = 1; 
            while (j < guest2.listDate.size()) {
                condition1(guest1, guest2, guest3, i, j);
                condition2(guest1, guest2, guest3, i, j);
                condition3(guest1, guest2, guest3, i, j);
                condition4(guest1, guest2, guest3, i, j);
                condition5(guest1, guest2, guest3, i, j);
                condition6(guest1, guest2, guest3, i, j);
                condition7(guest1, guest2, guest3, i, j);
                condition8(guest1, guest2, guest3, i, j);
                j = j + 2;
            }
            i = i + 2;
        }
        return guest3;
    }
    private static void condition1(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest2.listDate.get(j - 1).isAfter(guest1.listDate.get(i)) && guest2.listDate.get(j).isBefore(guest1.listDate.get(i + 1))) {
            guest3.listDate.add(guest2.listDate.get(j - 1));
            guest3.listDate.add(guest2.listDate.get(j));
        }
    }
    private static void condition2(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest1.listDate.get(i).isAfter(guest2.listDate.get(j - 1)) && guest1.listDate.get(i + 1).isBefore(guest2.listDate.get(j))) {
            guest3.listDate.add(guest1.listDate.get(i));
            guest3.listDate.add(guest1.listDate.get(i + 1));
        }
    }
    private static void condition3(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest2.listDate.get(j).isAfter(guest1.listDate.get(i)) && guest1.listDate.get(i).isAfter(guest2.listDate.get(j - 1)) && guest2.listDate.get(j).isBefore(guest1.listDate.get(i + 1))) {
            guest3.listDate.add(guest1.listDate.get(i));
            guest3.listDate.add(guest2.listDate.get(j));
        }
    }
    private static void condition4(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest1.listDate.get(i + 1).isAfter(guest2.listDate.get(j - 1)) && guest1.listDate.get(i).isBefore(guest2.listDate.get(j - 1)) && guest1.listDate.get(i + 1).isBefore(guest2.listDate.get(j))) {
            guest3.listDate.add(guest2.listDate.get(j - 1));
            guest3.listDate.add(guest1.listDate.get(i + 1));
        }
    }
    private static void condition5(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest1.listDate.get(i).isEqual(guest2.listDate.get(j - 1)) && guest1.listDate.get(i + 1).isBefore(guest2.listDate.get(j))) {
            guest3.listDate.add(guest1.listDate.get(i));
            guest3.listDate.add(guest1.listDate.get(i + 1));
        }
    }
    private static void condition6(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest1.listDate.get(i).isEqual(guest2.listDate.get(j - 1)) && guest1.listDate.get(i + 1).isEqual(guest2.listDate.get(j))) {
            guest3.listDate.add(guest1.listDate.get(i));
            guest3.listDate.add(guest1.listDate.get(i + 1));
        }
    }
    private static void condition7(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest1.listDate.get(i).isEqual(guest2.listDate.get(j - 1)) && guest1.listDate.get(i + 1).isAfter(guest2.listDate.get(j))) {
            guest3.listDate.add(guest1.listDate.get(i));
            guest3.listDate.add(guest2.listDate.get(j));
        }
    }
    private static void condition8(Guest guest1, Guest guest2, Guest guest3, int i, int j) {
        if (guest1.listDate.get(i + 1).isEqual(guest2.listDate.get(j)) && guest2.listDate.get(j - 1).isBefore(guest1.listDate.get(i))) {
            guest3.listDate.add(guest1.listDate.get(i));
            guest3.listDate.add(guest2.listDate.get(j));
        }
    }
}