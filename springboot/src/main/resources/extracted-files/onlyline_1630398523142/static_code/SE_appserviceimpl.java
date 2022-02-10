"package com.realnet.comm.service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Department;" + "\r\n" + 
"import com.realnet.comm.repository.departmentrepository;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class departmentserviceimpl implements departmentservice {" + "\r\n" + 
"" + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    private departmentrepository deptrepo;" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public Page<Department> getAll(Pageable page) {" + "\r\n" + 
"" + "\r\n" + 
"        Page<Department> find = this.deptrepo.findAll(page);" + "\r\n" + 
"" + "\r\n" + 
"        return find;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    // public List<Department> getdept()" + "\r\n" + 
"    // {" + "\r\n" + 
"    // List<Department> findAll = deptrepo.findAll();" + "\r\n" + 
"    // return findAll;" + "\r\n" + 
"    // }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public List<Department> save(List<Department> department) {" + "\r\n" + 
"        List<Department> saveAll = deptrepo.saveAll(department);" + "\r\n" + 
"        return saveAll;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public Department getone(int id) {" + "\r\n" + 
"        Department dept = deptrepo.findById(id)." + "\r\n" + 
"        		orElseThrow(() -> new ResourceNotFoundException(\"department not found :: \" + id));;" + "\r\n" + 
"        return dept;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public Department updatedept(int id, Department dept) {" + "\r\n" + 
"        Department dep = deptrepo.findById(id)" + "\r\n" + 
"                .orElseThrow(() -> new ResourceNotFoundException(\"department not found :: \" + id));" + "\r\n" + 
"" + "\r\n" + 
"        dep.setDcontact(dept.getDcontact());" + "\r\n" + 
"        dep.setDhead(dept.getDhead());" + "\r\n" + 
"        dep.setDname(dept.getDname());" + "\r\n" + 
"        dep.setNo_ofEmp(dept.getNo_ofEmp());" + "\r\n" + 
"        final Department newdept = deptrepo.save(dep);" + "\r\n" + 
"" + "\r\n" + 
"        return newdept;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public boolean delete(int id) {" + "\r\n" + 
"        if (!deptrepo.existsById(id)) {" + "\r\n" + 
"			" + "\r\n" + 
"            throw new ResourceNotFoundException(\" not found :: \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"		Department sale = deptrepo.findById(id).orElse(null);" + "\r\n" + 
"		deptrepo.delete(sale);" + "\r\n" + 
"        return true;" + "\r\n" + 
"    }" + "\r\n" + 
"    " + "\r\n" + 
"    " + "\r\n" + 
"}" 