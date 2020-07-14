package dsls

import org.apache.http.annotation.Contract

class TestMessage {

    def contractDsl = Contract.make {

        label 'message_label'
        input{

        }
    }
}
