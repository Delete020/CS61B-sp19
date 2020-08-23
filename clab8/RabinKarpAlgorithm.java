public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int inputLength = input.length();
        int patternLength = pattern.length();
        if (patternLength > inputLength) {
            return -1;
        }

        RollingString inputRolling = new RollingString(input.substring(0, patternLength), patternLength);
        RollingString patternRolling = new RollingString(pattern, patternLength);

        int hpattern = patternRolling.hashCode();
        int match = 0;
        for (int i = 0; i <= inputLength - patternLength; i++) {
            if (hpattern == inputRolling.hashCode()) {
                if (inputRolling.equals(patternRolling)) {
                    match += 1;
                }
            }
            if (patternLength + i < inputLength) {
                inputRolling.addChar(input.charAt(patternLength + i));
            }
        }

        if (match > 0) {
            return match;
        }
        return -1;
    }

}
