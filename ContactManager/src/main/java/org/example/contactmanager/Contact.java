package org.example.contactmanager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

    public class Contact {
        private final StringProperty name;
        private final StringProperty phone;
        private final StringProperty email;

        public Contact(String name, String phone, String email) {
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.email = new SimpleStringProperty(email);
        }


        public StringProperty nameProperty() {
            return name;
        }

        public StringProperty phoneProperty() {
            return phone;
        }

        public StringProperty emailProperty() {
            return email;
        }


        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getPhone() {
            return phone.get();
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String email) {
            this.email.set(email);
        }


        @Override
        public String toString() {
            return "Contact{" +
                    "name=" + name.get() +
                    ", phone=" + phone.get() +
                    ", email=" + email.get() +
                    '}';
        }
    }
