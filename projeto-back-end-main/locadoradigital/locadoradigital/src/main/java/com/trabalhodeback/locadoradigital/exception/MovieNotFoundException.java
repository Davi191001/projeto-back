package com.trabalhodeback.locadoradigital.exception;

public class MovieNotFoundException extends RuntimeException{

   public MovieNotFoundException(){ super("Filme não existe!"); }

}
