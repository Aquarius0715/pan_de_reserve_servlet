package jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.impl;

import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.UserDAO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dao.impl.UserDAOImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.database.mysql.dto.UserDTO;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.logic.UserResisterPageLogic;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.model.UserResisterPageModel;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.PasswordEncoder;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PageManagerImpl;
import jp.ac.kisarazu.j.fukuoka.pandereserve.servlet.admin.util.impl.PasswordEncoderImpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class UserResisterPageLogicImpl extends PageManagerImpl implements UserResisterPageLogic {

    public UserResisterPageLogicImpl() {

    }

    @Override
    public boolean resisterUser(UserResisterPageModel userResisterPageModel) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO = new UserDTO();
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        byte[] salt = passwordEncoder.generateSalt();
        String hashedPassword = passwordEncoder.hashPassword(userResisterPageModel.getPassword(), salt);

        userDTO.setUsername(userResisterPageModel.getUsername());
        userDTO.setPassword(hashedPassword);
        userDTO.setSalt(Base64.getEncoder().encodeToString(salt));
        return userDAO.storeUser(userDTO);
    }
}
