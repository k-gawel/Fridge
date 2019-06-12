package org.california.model.transfer.response;

import java.io.Serializable;
import java.time.LocalDate;


public class ItemInstanceDto implements Serializable {

    public Long id;
    public String comment;
    public LocalDate expireOn;

    public Long itemId;
    public Long containerId;

    public Long addedById;
    public LocalDate addedOn;

    public Long openById;
    public LocalDate openOn;

    public Long frozenById;
    public LocalDate frozenOn;

    public Long deletedById;
    public LocalDate deletedOn;
    

    @Override
    public String toString() {
        return "ItemInstanceDto{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", containerId=" + containerId +
                ", comment='" + comment + '\'' +
                ", expireOn=" + expireOn +
                ", addedById=" + addedById +
                ", addedOn=" + addedOn +
                ", openById=" + openById +
                ", openOn=" + openOn +
                ", frozenById=" + frozenById +
                ", frozenOn=" + frozenOn +
                ", deletedById=" + deletedById +
                ", deletedOn=" + deletedOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemInstanceDto that = (ItemInstanceDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (containerId != null ? !containerId.equals(that.containerId) : that.containerId != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (expireOn != null ? !expireOn.equals(that.expireOn) : that.expireOn != null) return false;
        if (addedById != null ? !addedById.equals(that.addedById) : that.addedById != null) return false;
        if (addedOn != null ? !addedOn.equals(that.addedOn) : that.addedOn != null) return false;
        if (openById != null ? !openById.equals(that.openById) : that.openById != null) return false;
        if (openOn != null ? !openOn.equals(that.openOn) : that.openOn != null) return false;
        if (frozenById != null ? !frozenById.equals(that.frozenById) : that.frozenById != null) return false;
        if (frozenOn != null ? !frozenOn.equals(that.frozenOn) : that.frozenOn != null) return false;
        if (deletedById != null ? !deletedById.equals(that.deletedById) : that.deletedById != null) return false;
        return deletedOn != null ? deletedOn.equals(that.deletedOn) : that.deletedOn == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (containerId != null ? containerId.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (expireOn != null ? expireOn.hashCode() : 0);
        result = 31 * result + (addedById != null ? addedById.hashCode() : 0);
        result = 31 * result + (addedOn != null ? addedOn.hashCode() : 0);
        result = 31 * result + (openById != null ? openById.hashCode() : 0);
        result = 31 * result + (openOn != null ? openOn.hashCode() : 0);
        result = 31 * result + (frozenById != null ? frozenById.hashCode() : 0);
        result = 31 * result + (frozenOn != null ? frozenOn.hashCode() : 0);
        result = 31 * result + (deletedById != null ? deletedById.hashCode() : 0);
        result = 31 * result + (deletedOn != null ? deletedOn.hashCode() : 0);
        return result;
    }


    public static class Builder {

        private ItemInstanceDto result = new ItemInstanceDto();


        public void setId(Long id) {
            result.id = id;
        }

        public void setItemId(Long itemId) {
            result.itemId = itemId;
        }

        public void setContainerId(Long containerId) {
            result.containerId = containerId;
        }

        public void setComment(String comment) {
            result.comment = comment;
        }

        public void setExpireOn(LocalDate expireOn) {
            result.expireOn = expireOn;
        }

        public void setAddedById(Long addedById) {
            result.addedById = addedById;
        }

        public void setAddedOn(LocalDate addedOn) {
            result.addedOn = addedOn;
        }

        public void setOpenById(Long openById) {
            result.openById = openById;
        }

        public void setOpenOn(LocalDate openOn) {
            result.openOn = openOn;
        }

        public void setFrozenById(Long frozenById) {
            result.frozenById = frozenById;
        }

        public void setFrozenOn(LocalDate frozenOn) {
            result.frozenOn = frozenOn;
        }

        public void setDeletedById(Long deletedById) {
            result.deletedById = deletedById;
        }

        public void setDeletedOn(LocalDate deletedOn) {
            result.deletedOn = deletedOn;
        }
        
        
        public ItemInstanceDto build() {
            if(result.id == null)
                throw new IllegalStateException("id");
            if(result.containerId == null)
                throw new IllegalStateException("container_id");
            if(result.addedOn == null || result.addedById == null)
                throw new IllegalStateException("added_properties");
            if((result.openById == null) != (result.openOn == null))
                throw new IllegalStateException("open_properties");
            if((result.frozenById == null) != (result.frozenOn == null))
                throw new IllegalStateException("frozen_properties");
            if((result.deletedById == null) != (result.deletedOn == null))
                throw new IllegalStateException("delete_properties");
            return result;
        }
        
    }


}
