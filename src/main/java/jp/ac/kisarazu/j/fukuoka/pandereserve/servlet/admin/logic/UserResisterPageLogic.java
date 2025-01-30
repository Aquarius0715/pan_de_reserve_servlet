package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic;

import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.UserResisterPageModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserResisterPageLogic {
    boolean resisterUser(UserResisterPageModel userResisterPageModel) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
