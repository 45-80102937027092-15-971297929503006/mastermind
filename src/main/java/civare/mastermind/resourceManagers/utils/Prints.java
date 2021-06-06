package civare.mastermind.resourceManagers.utils;

public class Prints {
	public void printFunctionName() {
		System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

	}

}
