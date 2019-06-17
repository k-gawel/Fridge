package org.california.model.transfer.response;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Item;
import org.california.model.entity.ItemInstance;
import org.california.model.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


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

        public CommentSetter setId(@NotNull Long id) {
            result.id = id;
            return new CommentSetter();
        }

        public CommentSetter setId(@NotNull ItemInstance itemInstance) {
            return setId(itemInstance.getId());
        }

        class CommentSetter {
            ExpireOnSetter setComment(String comment) {
                Builder.this.result.comment = comment;
                return new ExpireOnSetter();
            }
        }

        class ExpireOnSetter {
            public ItemIdSetter setExpireOn(LocalDate expireOn) {
                Builder.this.result.expireOn = expireOn;
                return new ItemIdSetter();
            }
        }

        class ItemIdSetter {
            ContainerIdSetter setItemId(@NotNull Long itemId) {
                Builder.this.result.itemId = itemId;
                return new ContainerIdSetter();
            }

            ContainerIdSetter setItemId(@NotNull Item item) {
                return setItemId(item.getId());
            }
        }

        class ContainerIdSetter {
            AddedByIdSetter setContainerId(@NotNull Long containerId) {
                Builder.this.result.containerId = containerId;
                return new AddedByIdSetter();
            }

            AddedByIdSetter setContainerId(@NotNull Container container) {
                return setContainerId(container.getId());
            }
        }

        class AddedByIdSetter {
            AddedOnSetter setAddedById(@NotNull Long addedById) {
                Builder.this.result.addedById = addedById;
                return new AddedOnSetter();
            }

            AddedOnSetter setAddedById(@NotNull Account addedBy) {
                return setAddedById(addedBy.getId());
            }
        }

        class AddedOnSetter {
            OpenByIdSetter setAddedOn(@NotNull LocalDate date) {
                Builder.this.result.addedOn = date;
                return new OpenByIdSetter();
            }
        }

        class OpenByIdSetter {
            OpenOnSetter setOpenById(Long openById) {
                Builder.this.result.openById = openById;
                return new OpenOnSetter();
            }

            OpenOnSetter setOpenById(Account openBy) {
                return setOpenById(openBy != null ? openBy.getId() : null);
            }
        }

        class OpenOnSetter {
            FrozenByIdSetter setOpenOn(LocalDate date) {
                ObjectUtils.allAreNullOrNoneIs(date, Builder.this.result.openById);

                Builder.this.result.openOn = date;
                return new FrozenByIdSetter();
            }
        }

        class FrozenByIdSetter {
            FrozenOnSetter setFrozenById(Long frozenById) {
                Builder.this.result.frozenById = frozenById;
                return new FrozenOnSetter();
            }

            FrozenOnSetter setFrozenById(Account frozenBy) {
                return setFrozenById(frozenBy != null ? frozenBy.getId() : null);
            }
        }

        class FrozenOnSetter {
            DeletedByIdSetter setFrozenOn(LocalDate localDate) {
                ObjectUtils.allAreNullOrNoneIs(localDate, Builder.this.result.frozenById);
                Builder.this.result.frozenOn = localDate;

                return new DeletedByIdSetter();
            }
        }

        class DeletedByIdSetter {
            DeletedOnSetter setDeletedById(Long deletedById) {
                Builder.this.result.deletedById = deletedById;
                return new DeletedOnSetter();
            }

            DeletedOnSetter setDeletedById(Account account) {
                return setDeletedById(account != null ? account.getId() : null);
            }
        }

        class DeletedOnSetter {
            FinalBuilder setDeletedOn(LocalDate deletedOn) {
                ObjectUtils.allAreNullOrNoneIs(deletedOn, Builder.this.result.deletedById);

                Builder.this.result.deletedOn = deletedOn;

                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            ItemInstanceDto build() {
                return Builder.this.result;
            }
        }



    }


}
