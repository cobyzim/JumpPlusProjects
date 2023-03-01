package regex;

public class Regex {
	
	public static boolean validateAddress(String address) {
        return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }

    public static boolean validateName(String name) {
        return name.matches("^[A-Z]\\w+\\s[A-Z]\\w+");
    }

    public static boolean validatePhoneNumber(int number) {
        String num = Integer.toString(number);
        return num.matches("[0-9]{10}");
    }

    public static boolean validateUserId(int number) {
        String num = Integer.toString(number);
        return num.matches("[1-9]+");
    }

    public static boolean validatePassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8}$");
    }

    public static boolean validateInitialDeposit(double number) {
        String num = Double.toString(number);
        return num.matches("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
    }

}
