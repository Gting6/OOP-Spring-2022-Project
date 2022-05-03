package application;

public enum ViewEnum {
    MEMBER {
        @Override
        String getFxmlFile() {
            return "/view/Member.fxml";
        }
    }, LOGIN {
        @Override
        String getFxmlFile() {
            return "/view/Login.fxml";
        }
    }, RESTAURANT {
        @Override
        String getFxmlFile() {
            return "/view/Restaurant.fxml";
        }
    }, DELIVER {
        @Override
        String getFxmlFile() {
            return "/view/Deliver.fxml";
        }
    };
    abstract String getFxmlFile();
}
