/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Natalia
 */
public class validarEmail {
    private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
//valida correo electronico
public static boolean validateEmail(String email) {
	try{
	    // Compiles the given regular expression into a pattern.
	    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	    // Match the given input against this pattern
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}catch(Exception e){
		e.printStackTrace();
	}
	return false;
}
    
}
