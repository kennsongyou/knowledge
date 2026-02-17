package ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AuditPO extends CreatedAuditPO {

    @TableField(fill = FieldFill.INSERT_UPDATE)
    Long updatedBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    Instant updatedAt;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    Integer deleted;

}
