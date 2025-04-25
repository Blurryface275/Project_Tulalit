/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server_project;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import static server_project.ServerTCP.daftarPengguna;
import static server_project.ServerTCP.listTiket;
import static server_project.ServerTCP.keranjangUser;

/**
 *
 * @author hanse
 */
public class HandleSocket extends Thread {

    Socket client;
    BufferedReader requestClient;
    DataOutputStream msgToClient;

    public HandleSocket(Socket _client) {

        this.client = _client;

        try {
            requestClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            msgToClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            String message = requestClient.readLine();
            System.out.println(message);
            if (message == null) {
                client.close();
                return;
            }

            String[] commands = message.split(" ", 3);

            if (commands[0].equals("LOGIN")) { // masuk sini kalo request klien login
                String hasilLogin = checkLogin(commands[1], commands[2]);
                msgToClient.writeBytes(hasilLogin + "\n");

            } else if (commands[0].equals("GET_LIST_TICKETS")) { // masuk sini kalo request klien list cuy
                String hasilKirim = convertListToString("normal", "");
                msgToClient.writeBytes(hasilKirim + "\n");
                System.out.println("normal");
            } else if (commands[0].equals("QUERY")) {
                String hasilKirim = null;
                String query = commands[2];
                System.out.println("masuk query");
                if (commands[1].equals("NAMA")) {
                    hasilKirim = convertListToString("ceknama", query);
                    System.out.println("cek nama");
                    msgToClient.writeBytes(hasilKirim + "\n");
                System.out.println(hasilKirim);
                } else if (commands[1].equals("JENIS")) {
                    hasilKirim = convertListToString("cekjenis", query);
                    System.out.println("cek jenis");
                    msgToClient.writeBytes(hasilKirim + "\n");
                System.out.println(hasilKirim);
                } else if (commands[1].equals("TANGGAL")) {
                    hasilKirim = convertListToString("cektanggal", query);
                    System.out.println("cek tanggal");
                    msgToClient.writeBytes(hasilKirim + "\n");
                System.out.println(hasilKirim);
                }

                
            } else if (commands[0].equals("REGISTER")) { // masuk sini kalo request klien register
                if (commands.length >= 8) {
                    String name = commands[1];
                    String username = commands[2];
                    String password = commands[3];
                    String no_hp = commands[4];
                    String email = commands[5];
                    LocalDate tanggalLahirStr = LocalDate.parse(commands[6]);
                    String role = commands[7];
                    LocalDate memberSinceStr = LocalDate.parse(commands[8]);

                    boolean usernameExists = false;
                    for (Pengguna p : daftarPengguna) {
                        if (p.getUsername().equals(username)) {
                            usernameExists = true;
                            break;
                        }
                    }

                    if (usernameExists) {
                        msgToClient.writeBytes("Username already exists\n");
                    } else {

                        Pengguna penggunaBaru = new Pengguna(daftarPengguna.size() + 1, name, username, password, email, no_hp, role, tanggalLahirStr, memberSinceStr);
                        daftarPengguna.add(penggunaBaru);
                        msgToClient.writeBytes("Registration successful\n");

                    }
                } else {
                    msgToClient.writeBytes("Invalid REGISTER format\n");
                }
            } else if (commands[0].equals("GET_KERANJANG")) {
                String hasilKirim = convertListKeranjangToString();
                msgToClient.writeBytes(hasilKirim + "\n");

            } else if (commands[0].equals("CHECKOUT")) {
                if (commands.length >= 2) {
                    String username = commands[1];
                    String hasilKirim = Checkout(username);
                    msgToClient.writeBytes(hasilKirim + "\n");
                }
            }
            client.close();
            System.out.println("Koneksi ditutup.\n");
        } catch (Exception e) {
            System.out.println("Error di " + e);
        }

    }

    public static String checkLogin(String username, String password) { // buat cek login sesuai namanya, di return true + rolenya , kalo salah false
        for (Pengguna p : daftarPengguna) {
            if (p.getUsername().equals(username)
                    && p.getPassword().equals(password)) {
                return "TRUE/" + p.getRole() + "/" + p.getUsername();
            }
        }
        return "FALSE";
    }

    private static String convertListToString(String command, String query) {
        StringBuilder sb = new StringBuilder();
        if (command.equals("normal")) {
            for (Tiket t : listTiket) {
                sb.append(t.getTiketCloseDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                        .append(t.getCreatorName()).append("|")
                        .append(t.getEventName()).append("|")
                        .append(t.getLocation()).append("|")
                        .append(t.getEventDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                        .append(t.getRegPrice()).append("|")
                        .append(t.getVipPrice()).append("|")
                        .append(t.getStock())
                        .append("#");
            }

        } else if (command.equals("ceknama")) {
            for (Tiket t : listTiket) {
                if (t.getEventName().toLowerCase().contains(query.toLowerCase())) {
                    sb.append(t.getTiketCloseDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                            .append(t.getCreatorName()).append("|")
                            .append(t.getEventName()).append("|")
                            .append(t.getLocation()).append("|")
                            .append(t.getEventDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                            .append(t.getRegPrice()).append("|")
                            .append(t.getVipPrice()).append("|")
                            .append(t.getStock())
                            .append("#");
                }
            }

        } else if (command.equals("cekjenis")) {
            for (Tiket t : listTiket) {
                if (t.getKategori().toLowerCase().contains(query.toLowerCase())) {
                    sb.append(t.getTiketCloseDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                            .append(t.getCreatorName()).append("|")
                            .append(t.getEventName()).append("|")
                            .append(t.getLocation()).append("|")
                            .append(t.getEventDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                            .append(t.getRegPrice()).append("|")
                            .append(t.getVipPrice()).append("|")
                            .append(t.getStock())
                            .append("#");
                }
            }

        } else if (command.equals("cektanggal")) {
            LocalDate queryDate = LocalDate.parse(query); 
            for (Tiket t : listTiket) {
                if (t.getEventDate().equals(queryDate)) {
                    sb.append(t.getTiketCloseDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                            .append(t.getCreatorName()).append("|")
                            .append(t.getEventName()).append("|")
                            .append(t.getLocation()).append("|")
                            .append(t.getEventDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()).append("|")
                            .append(t.getRegPrice()).append("|")
                            .append(t.getVipPrice()).append("|")
                            .append(t.getStock())
                            .append("#");
                }
            }
        }
        return sb.toString();
    }

    private static String convertListKeranjangToString() {
        StringBuilder sb = new StringBuilder();

        for (Keranjang k : keranjangUser) {
            sb.append(k.getUsernameBuyer()).append("|")
                    .append(k.getCreatorName()).append("|")
                    .append(k.getEventName()).append("|")
                    .append(k.getJenis()).append("|")
                    .append(k.getPrice()).append("|")
                    .append(k.getQuantity()).append("|")
                    .append(k.getTotal())
                    .append("#");
        }
        String result = sb.toString();
        return result;
    }

    private static String Checkout(String username) {
        StringBuilder sb = new StringBuilder();
        Iterator<Keranjang> iterator = keranjangUser.iterator();

        while (iterator.hasNext()) {
            Keranjang k = iterator.next();
            if (k.getUsernameBuyer().equals(username)) {
                // Hapus data dari list
                iterator.remove();
            } else {
                sb.append(k.getUsernameBuyer()).append("|")
                        .append(k.getCreatorName()).append("|")
                        .append(k.getEventName()).append("|")
                        .append(k.getJenis()).append("|")
                        .append(k.getPrice()).append("|")
                        .append(k.getQuantity()).append("|")
                        .append(k.getTotal())
                        .append("#");
            }
        }
        return sb.toString();
    }
}
