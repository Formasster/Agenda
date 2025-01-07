package es.educastur.agenda2v;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RubtsovaAlesia {
    
    //DECLARO UN ARRAYLIST llamado contactos como atributo de la clase Agenda2024
    ArrayList<Contacto> contactos;
    
    //constructor de la clase Agenda2024
    public RubtsovaAlesia(){
        contactos=new ArrayList();
    }
    
    public static void main(String[] args) {
        /*creo un ÚNICO objeto de la clase Agenda2024 y llamo a cargaDatos() y menu()
        es necesario aquí poner agenda.cargaDatos() y agenda.menu() ya que desde un método static
        como el main no se puede llamar directamente a métodos no static */
        RubtsovaAlesia agenda=new RubtsovaAlesia();
        agenda.cargaDatos();
        agenda.menu();
    }
    
    public void menu(){
        Scanner sc=new Scanner (System.in);
        int opcion=0;
        do{
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES\n");
            System.out.println("\t\t\t\t1 - NUEVO CONTACTO");
            System.out.println("\t\t\t\t2 - LISTA DE CONTACTOS");
            System.out.println("\t\t\t\t3 - MODIFICAR CONTACTO");
            System.out.println("\t\t\t\t4 - BORRAR CONTACTO");
            System.out.println("\t\t\t\t5 - LISTAR CUMPLEAÑOS");
            System.out.println("\t\t\t\t6 - ¿QUIÉN CUMPLE HOY?");
            System.out.println("\t\t\t\t7 - BORRAR CONTACTOS");
            System.out.println("\t\t\t\t8 - MAYOR EDAD");
            System.out.println("\t\t\t\t9 - SALIR");
            opcion=sc.nextInt();
            switch (opcion){
                case 1:{
                    nuevoContacto();
                    break;
                }    
                case 2:{
                    listaContactos();
                    break;
                } 
                case 3:{
                    modificarContacto();
                    break;
                } 
                case 4:{
                    borrarContacto();
                    break;
                } 
                case 5:{
                    listaCumpleaños();
                    break;
                } 
                case 6:{
                    cumpleañosHoy();
                    break;
                } 
                case 7:{
                    borrarContactos();
                    break;
                }
                case 8:{
                    mayorEdad();
                    break;
                }
            }
        }while (opcion != 9);
    }
       
    public void cargaDatos(){
        contactos.add(new Contacto("Ana","666666000","ana@hotmail.com ",LocalDate.parse("2000-01-01")));
        contactos.add(new Contacto("Pepe","666666111","pepe@hotmail.com",LocalDate.parse("2001-01-01")));
        contactos.add(new Contacto("Eva","666666222","eva@hotmail.com ", LocalDate.parse("2002-12-02")));
        contactos.add(new Contacto("Julia","666666333","julia@hotmail.com", LocalDate.parse("2003-03-03")));
        contactos.add(new Contacto("Luis","666666444","luis@hotmail.com", LocalDate.parse("2004-04-04")));
        contactos.add(new Contacto("Bea","666666555","bea@hotmail.com ", LocalDate.parse("2005-12-02")));
        contactos.add(new Contacto("Lucas","666666666","lucas@hotmail.com", LocalDate.parse("2006-06-06")));
        contactos.add(new Contacto("Olivia","666666777","olivia@hotmail.com", LocalDate.parse("2007-02-28")));
        contactos.add(new Contacto("Tomas","666666888","tomas@hotmail.com", LocalDate.parse("2008-03-01")));
        contactos.add(new Contacto("Marta","666666999","marta@hotmail.com", LocalDate.parse("2009-03-07")));
        Collections.sort(contactos);   
    }
    
    public void nuevoContacto(){
        String  nombre, telefono, email, fechaNac;
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Nuevo Contacto:");
        //Entrada del Nombre del nuevo contacto - SIN VALIDACIÓN
        System.out.println("Nombre:");
        nombre=sc.nextLine();
        //Entrada del TELEFONO del nuevo contacto - CON VALIDACIÓN MEDIANTE EXPRESIÓN REGULAR
        do{
                System.out.println("TELEFONO:");
                telefono=sc.next();
        }while(!telefono.matches("[6-7][0-9]{8}")); 
        //Entrada del EMAIL del nuevo contacto - CON VALIDACIÓN MEDIANTE EXPRESIÓN REGULAR
        do{
                System.out.println("EMAIL:");
                email=sc.next();
        }while(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")); 
         //Entrada de LA FECHA de nacimiento del nuevo contacto - CON VALIDACIÓN MEDIANTE EXPRESIÓN REGULAR
        do{
            System.out.println("FECHA DE NACIMIENTO: (AÑO-MES-DÍA)");
            //SI QUEREMOS USAR OTRO FORMATO DE FECHA DISTINTO (Dia-mes-año) hay que usar un DateTimeFormatter
            fechaNac = sc.next();
        }while(!fechaNac.matches("(19|20)(((([02468][048])|([13579][26]))-02-29)|(\\d{2})-((02-((0[1-9])|1\\d|2[0-8]))|((((0[13456789])|1[012]))-((0[1-9])|((1|2)\\d)|30))|(((0[13578])|(1[02]))-31)))"));      

        contactos.add(new Contacto(nombre,telefono,email,LocalDate.parse(fechaNac)));
        Collections.sort(contactos);
    }

    public void listaContactos() {
        for (Contacto c : contactos) {
            System.out.println(c);
        }
    }

    public void modificarContacto() {
        Scanner sc=new Scanner (System.in);
        System.out.println("Nombre del contacto a modificar: ");
        String nombre=sc.nextLine();
        int pos=buscaContacto(nombre);
        if (pos==-1){
            System.out.println("El nombre ue buscas no está en la agenda"); 
        }else{
            System.out.println("Teclea el nuevo teléfono:)");
            contactos.get(pos).setTelefono(sc.nextLine());
        }
    }

    public void borrarContacto() {
        Scanner sc=new Scanner (System.in);
        System.out.println("Nombre del contacto a Borrar: ");
        String nombre=sc.nextLine();
        int pos=buscaContacto(nombre);
        if (pos==-1){
            System.out.println("El nombre que buscas no está en la agenda"); 
        }else{
            contactos.remove(pos);
        }
    }
    
    public void listaCumpleaños(){
        Scanner sc=new Scanner (System.in);
        int dias=0;
        LocalDate hoy=LocalDate.now();
        System.out.println(hoy);
        System.out.println("Cumpleaños en los próximos X días:");
        System.out.println("Valor de X?");
        dias=sc.nextInt();
        
        if (hoy.isLeapYear() && hoy.getDayOfYear()>59){
                System.out.println("Estamos en año bisiesto");
                hoy=hoy.minusDays(1);
        }
        
        for (Contacto c : contactos) {
            int dif= c.getFechaNac().getDayOfYear()-hoy.getDayOfYear();
            if (dif>=0 && dif<=dias){
                System.out.println(c);
            }
        }
    }

    public void cumpleañosHoy(){
        LocalDate hoy = LocalDate.now();
        int hoyDia = hoy.getDayOfMonth();
        int hoyMes = LocalDate.now().getMonthValue();
        int cumpleDia;
        int cumpleMes;
        for (Contacto contacto : contactos) {
            cumpleDia = contacto.getFechaNac().getDayOfMonth();
            cumpleMes = contacto.getFechaNac().getMonthValue();
            if (cumpleMes==hoyMes && cumpleDia==hoyDia){
                System.out.println(contacto);
            }
        }
    }
    
    public void borrarContactos(){
        Scanner sc=new Scanner(System.in);
        ArrayList <String> nombres = new ArrayList<>();
        System.out.println("Dime los nombres que quieras eliminar. Para finalizar teclea FIN");
        System.out.println("NOMBRE: ");
        String nombre= sc.nextLine();
        while (!nombre.equalsIgnoreCase("FIN")){
            nombres.add(nombre);
            System.out.println("NOMBRE:");
            nombre= sc.nextLine();
            
        }
        for (String n : nombres) {
            int pos=buscaContacto(nombre);
            if (pos!=-1) {
                contactos.remove(pos);
            }
        }
        /*for (int i = 0; i < nombres.size(); i++) {
            int pos=buscaContacto(nombre);
            if (pos==-1){
            System.out.println("El nombre que buscas no está en la agenda"); 
            }else{
            contactos.remove(pos);
            }
        }  */      
 
    }
    
    public void mayorEdad (){
        LocalDate hoy = LocalDate.now();
        
        Collections.sort(contactos);
        System.out.println("El mayor de edad de la lista es: " + contactos.getFirst() + " que tiene " + 
            ChronoUnit.YEARS.between(contactos.getFirst().getFechaNac(), hoy) + " años");
        System.out.println("El menor de edad es: " + contactos.getLast() + "que tiene " + ChronoUnit.YEARS.between(contactos.getLast().getFechaNac(), hoy));
        
        
    }
    
    public int buscaContacto (String nombre){
        int pos=-1;
        for (int i = 0; i < contactos.size(); i++) {
            if(contactos.get(i).getNombre().equalsIgnoreCase(nombre)){
                pos=i;
                break;
            }
        }
        return pos;
    }
}