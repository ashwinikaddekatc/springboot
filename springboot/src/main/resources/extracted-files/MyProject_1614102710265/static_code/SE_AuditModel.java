"package com.realnet." + module_name + ".model;" + "\r\n" + 
"" + "\r\n" + 
"import java.io.Serializable;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.EntityListeners;" + "\r\n" + 
"import javax.persistence.MappedSuperclass;" + "\r\n" + 
"import javax.persistence.Temporal;" + "\r\n" + 
"import javax.persistence.TemporalType;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.annotation.CreatedDate;" + "\r\n" + 
"import org.springframework.data.annotation.LastModifiedDate;" + "\r\n" + 
"import org.springframework.data.jpa.domain.support.AuditingEntityListener;" + "\r\n" + 
"" + "\r\n" + 
"@MappedSuperclass" + "\r\n" + 
"@EntityListeners(AuditingEntityListener.class)" + "\r\n" + 
"" + "\r\n" + 
"public abstract class AuditModel implements Serializable {" + "\r\n" + 
"" + "\r\n" + 
"	private static final long serialVersionUID = 1L;" + "\r\n" + 
"" + "\r\n" + 
"	@Temporal(TemporalType.TIMESTAMP)" + "\r\n" + 
"    @Column(name = \"created_at\", nullable = false, updatable = false)" + "\r\n" + 
"    @CreatedDate" + "\r\n" + 
"    private Date createdAt;" + "\r\n" + 
"" + "\r\n" + 
"    @Temporal(TemporalType.TIMESTAMP)" + "\r\n" + 
"    @Column(name = \"updated_at\", nullable = false)" + "\r\n" + 
"    @LastModifiedDate" + "\r\n" + 
"    private Date updatedAt;" + "\r\n" + 
"" + "\r\n" + 
"    public Date getCreatedAt() {" + "\r\n" + 
"        return createdAt;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    public void setCreatedAt(Date createdAt) {" + "\r\n" + 
"        this.createdAt = createdAt;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    public Date getUpdatedAt() {" + "\r\n" + 
"        return updatedAt;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    public void setUpdatedAt(Date updatedAt) {" + "\r\n" + 
"        this.updatedAt = updatedAt;" + "\r\n" + 
"        sysout(\"vsdf);" + "\r\n" + 
"    }" + "\r\n" + 
"}" 
