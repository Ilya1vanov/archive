package client.view;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.spring.data.model.Role;
import server.spring.data.model.UserEntity;

import javax.swing.*;

/**
 * @author Ilya Ivanov
 */
@Component
@SuppressWarnings("deprecated")
public class UserForm extends Form<UserEntity> {
    /** login field */
    private JTextField login;

    /** role field */
    private JComboBox<Role> role;

    @Autowired
    UserForm(AbstractAction editAction, AbstractAction createAction, AbstractAction deleteAction, AbstractAction requestAction) {
        super(editAction, createAction, deleteAction, requestAction,"/users");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void build(DefaultFormBuilder builder) {
        login = new JTextField(10);
        role = new JComboBox<>();

        final Role[] values = Role.values();

        for (Role value : values) {
            role.addItem(value);
        }

        builder.append("Login: ", login);
        login.setEnabled(false);
        builder.nextLine();

        builder.append("Role: ", role);
        builder.nextLine();
    }

    @Override
    void finallyEdit() {
        object.setRole(Role.values()[role.getSelectedIndex()]);
    }

    @Override
    void finallyCreate() {
    }

    @Override
    boolean isValidInner() {
        final String loginText = login.getText();
        return !loginText.isEmpty();
    }

    @Override
    void setEnable(boolean enable) {
        role.setEnabled(enable);
    }

    /** nothing to open */
    @Override
    void createInner() {
    }

    @Override
    public void openInner() {
        login.setText(object.getLogin());
        role.setSelectedItem(object.getRole());
    }
}
