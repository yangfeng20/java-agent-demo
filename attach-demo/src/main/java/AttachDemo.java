import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;
import java.util.Scanner;

/**
 * @author yangfeng
 * @date : 2023/8/4 17:39
 * desc:
 */

public class AttachDemo {
    public static void main(String[] args) throws Exception{
        System.out.println("------------------------attach main ------------------------");

        List<VirtualMachineDescriptor> virtualMachineDescriptorList = VirtualMachine.list();
        System.out.println("请输入attach的进程id：");
        String pid = new Scanner(System.in).next();
        for (VirtualMachineDescriptor virtualMachineDescriptor : virtualMachineDescriptorList) {
            if (virtualMachineDescriptor.id().equals(pid)) {
                VirtualMachine virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
                virtualMachine.loadAgent("D:\\Program Dev\\Java\\05-Dev\\java-agent-demo\\agent-demo\\target\\agent-1.0-SNAPSHOT.jar", "");
                break;
            }
        }


        System.out.println("run start");
        Thread.sleep(10000000L);
        System.out.println("run end");
    }
}
