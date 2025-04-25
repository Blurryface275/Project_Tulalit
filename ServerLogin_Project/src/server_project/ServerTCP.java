/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server_project;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicole
 */
public class ServerTCP {

    static ArrayList<Pengguna> daftarPengguna = new ArrayList<>();
    static ArrayList<Tiket> listTiket = new ArrayList<>();
    static ArrayList<Keranjang> keranjangUser = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    try {
        userCreate(); // dummy user
        initSampleData(); // dummy tiket
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Server siap menerima koneksi...");

        while (true) {
            Socket incoming = serverSocket.accept();
            System.out.println("Koneksi diterima dari: " + incoming.getInetAddress());

            HandleSocket handler = new HandleSocket(incoming);
            handler.start(); 
        }

    } catch (IOException ex) {
        Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public static void userCreate() { // dummy user, biar gampang testing
        daftarPengguna.add(new Pengguna(
                1, "Nicole Putri", "nicole", "n123", "nicole@email.com", "0812345678", "Customer", LocalDate.of(2003, 5, 10), LocalDate.of(2024, 1, 1)
        ));
        daftarPengguna.add(new Pengguna(
                2, "Steve Angelo", "steve", "s123", "steve@email.com",
                "0823456789", "Customer", LocalDate.of(2002, 7, 22), LocalDate.of(2024, 1, 1)
        ));
        daftarPengguna.add(new Pengguna(
                3, "Hansen Lie", "hansen", "h123", "hansen@email.com",
                "0898765432", "Admin", LocalDate.of(2000, 12, 5), LocalDate.of(2023, 6, 10)
        ));
        daftarPengguna.add(new Pengguna(
                4, "Daren Wijaya", "daren", "d123", "daren@email.com",
                "0897654211", "Customer", LocalDate.of(2001, 9, 15), LocalDate.of(2024, 2, 28)
        ));
    }

    private static void initSampleData() {
        Tiket t1 = new Tiket("Watchify Team", "Konser Musik A", "Jakarta",
                LocalDate.of(2025, 5, 10), LocalTime.of(19, 0),
                100, 50, 150000, 250000,
                LocalDateTime.of(2025, 5, 9, 23, 59), "konser");

        Tiket t2 = new Tiket("Event Squad", "Talkshow Startup", "Surabaya",
                LocalDate.of(2025, 5, 12), LocalTime.of(14, 0),
                80, 30, 75000, 150000,
                LocalDateTime.of(2025, 5, 11, 23, 59), "konser");

        Tiket t3 = new Tiket("EduNation", "Workshop Java", "Bandung",
                LocalDate.of(2025, 5, 15), LocalTime.of(10, 0),
                60, 20, 50000, 100000,
                LocalDateTime.of(2025, 5, 14, 23, 59), "konser");

        Tiket t4 = new Tiket("MovieFest", "Nonton Bareng", "Yogyakarta",
                LocalDate.of(2025, 5, 18), LocalTime.of(20, 0),
                120, 60, 40000, 80000,
                LocalDateTime.of(2025, 5, 17, 23, 59), "film");

        Tiket t5 = new Tiket("CreativeHub", "Art Exhibition", "Bali",
                LocalDate.of(2025, 5, 20), LocalTime.of(11, 0),
                50, 25, 100000, 180000,
                LocalDateTime.of(2025, 5, 19, 23, 59), "event");

        Tiket t6 = new Tiket("GameVerse", "E-Sports Tournament", "Medan",
                LocalDate.of(2025, 5, 22), LocalTime.of(15, 0),
                70, 40, 85000, 160000,
                LocalDateTime.of(2025, 5, 21, 23, 59), "game");

        Tiket t7 = new Tiket("CineClub", "Pemutaran Film Indie", "Malang",
                LocalDate.of(2025, 5, 25), LocalTime.of(18, 30),
                90, 45, 30000, 60000,
                LocalDateTime.of(2025, 5, 24, 23, 59), "film");

        Tiket t8 = new Tiket("BizTalks", "Seminar Kewirausahaan", "Semarang",
                LocalDate.of(2025, 5, 27), LocalTime.of(9, 0),
                65, 35, 60000, 120000,
                LocalDateTime.of(2025, 5, 26, 23, 59), "seminar");

        Tiket t9 = new Tiket("BookLovers", "Festival Buku", "Padang",
                LocalDate.of(2025, 5, 28), LocalTime.of(10, 30),
                150, 75, 20000, 50000,
                LocalDateTime.of(2025, 5, 27, 23, 59), "seminar");

        Tiket t10 = new Tiket("DanceNation", "Kompetisi Tari Modern", "Makassar",
                LocalDate.of(2025, 5, 30), LocalTime.of(16, 0),
                40, 20, 70000, 140000,
                LocalDateTime.of(2025, 5, 29, 23, 59), "kompetisi");
        
        
        listTiket.add(t1);
        listTiket.add(t2);
        listTiket.add(t3);
        listTiket.add(t4);
        listTiket.add(t5);
        listTiket.add(t6);
        listTiket.add(t7);
        listTiket.add(t8);
        listTiket.add(t9);
        listTiket.add(t10);
        
        Keranjang k1 = new Keranjang(
            "daren",
            "Watchify Team",
            "Konser Musik A",
            "VIP",
            250000,
            2
        );
        
        Keranjang k2 = new Keranjang(
            "daren",
            "Event Squad",
            "Talkshow Startup",
            "Reguler",
            75000,
            2
        );
        
        Keranjang k3 = new Keranjang(
            "daren",
            "Event Squad",
            "Talkshow Startup",
            "VIP",
            150000,
            1
        );
        
        Keranjang k4 = new Keranjang(
            "steve",
            "Event Squad",
            "Talkshow Startup",
            "VIP",
            150000,
            5
        );
        
        keranjangUser.add(k1);
        keranjangUser.add(k2);
        keranjangUser.add(k3);
        keranjangUser.add(k4);
    }


}
