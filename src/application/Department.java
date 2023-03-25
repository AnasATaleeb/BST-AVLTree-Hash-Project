package application;

public class Department implements Comparable<Department> {
	private String departmentName;
	private String departmentRelatedDataFileName;
	private Hash<Student> hashTable ;

	
	
	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(String departmentName, String departmentRelatedDataFileName) {
		super();
		this.departmentName = departmentName; 
		this.departmentRelatedDataFileName = departmentRelatedDataFileName;
		this.hashTable = new Hash<>() ;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentRelatedDataFileName() {
		return departmentRelatedDataFileName;
	}

	public void setDepartmentRelatedDataFileName(String departmentRelatedDataFileName) {
		this.departmentRelatedDataFileName = departmentRelatedDataFileName;
	}


	public Hash<Student> getHashTable() {
		return hashTable;
	} 

	public void setHashTable(Hash<Student> hashTable) {
		this.hashTable = hashTable;
	}

	@Override
	public int compareTo(Department o) {
		return this.departmentName.compareTo(o.departmentName);
	} 
	
	@Override
	public String toString() {
		return departmentName + "/"
				+ departmentRelatedDataFileName ;
	}
	
}
