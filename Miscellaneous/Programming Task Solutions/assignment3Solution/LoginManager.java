package assignment3Solution;

import assignment1Solution.NotaGenerator;
import assignment3Solution.user.Member;
import assignment3Solution.user.menu.EmployeeSystem;
import assignment3Solution.user.menu.MemberSystem;
import assignment3Solution.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        return null;
    }

    public Member register(String nama, String noHp, String password) {
        String id = NotaGenerator.generateId(nama, noHp);
        if(memberSystem.isMemberExist(id)){
            return null;
        }

        Member member = new Member(nama, id, password);
        memberSystem.addMember(member);
        return member;
    }
}
