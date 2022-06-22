package stepdefinitions;





import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class stepDefinitions {

	@Given("^User is on banking landing page$")
	public void user_is_on_banking_landing_page() throws Throwable {
		System.out.println("Landing page");


	}


	@Then("^Home page is populated$")
	public void home_page_is_displayed() throws Throwable {
		System.out.println("Home page is displayed");

	}




	@When("User login into application with username {string} and password {string}")
	public void userLoginIntoApplicationWithUsernameAndPassword(String uname, String pass) {

		System.out.println("User logged in with "+uname+" and "+pass+"");
		
	}



	@And("Cards are displayed {string}")
	public void cardsAreDisplayed(String arg0) {
		System.out.println("Cards are displayed "+arg0);
	}


}
