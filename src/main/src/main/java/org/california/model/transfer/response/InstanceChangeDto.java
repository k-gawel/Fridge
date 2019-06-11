package org.california.model.transfer.response;

import org.california.model.util.ChangeOnInstance;

import java.io.Serializable;
import java.time.LocalDate;

public class InstanceChangeDto implements Serializable {


    public Long id;
    public ItemInstanceDto instance;
    public Long accountId;
    public LocalDate changeDate;
    public ChangeOnInstance changeType;



    public static class Builder {

        private InstanceChangeDto result = new InstanceChangeDto();

        public void setId(Long id) {
            result.id = id;
        }

        public void setInstance(ItemInstanceDto instance) {
            result.instance = instance;
        }

        public void setAccountId(Long accountId) {
            result.accountId = accountId;
        }

        public void setChangeDate(LocalDate changeDate) {
            result.changeDate = changeDate;
        }

        public void setChange(ChangeOnInstance change) {
            result.changeType = change;
        }


        public InstanceChangeDto build() {
            if(result.id == null || result.instance == null || result.accountId == null
                    || result.changeDate == null || result.changeType == null)
                throw new IllegalStateException("params can't be null");

            return result;
        }

    }

}
