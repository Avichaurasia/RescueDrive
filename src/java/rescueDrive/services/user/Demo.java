/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rescueDrive.services.user;

import java.util.Random;

/**
 *
 * @author User
 */
public class Demo {
 
    
    public static void main(String[] args) {
        
        System.out.println(generateCode());
    }
            
    public static String generateCode() {
        String str = "";
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            str += r.nextInt(8);
        }
        return str;
    }
}
