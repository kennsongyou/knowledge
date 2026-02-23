package ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class CreatedAuditPO {

    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    Long createdBy;

    @TableField(fill = FieldFill.INSERT)
    Instant createdAt;

    @TableField(fill = FieldFill.INSERT)
    Long tenantId;

}
