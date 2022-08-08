package com.techelevator.model;

/**
 * DTO for storing a user's credentials.
 */
public class LoginDTO {

   private String email;
   private String password;

   public String getEmail() {
      return email;
   }

   public void setUsername(String username) {
      this.email = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "LoginDTO{" +
              "username='" + email + '\'' +
              ", password='" + password + '\'' +
              '}';
   }
}
