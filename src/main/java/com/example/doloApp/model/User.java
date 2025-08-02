package com.example.doloApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User entity representing application users.
 *
 * The 'role' field can be:
 *   - sender: Can send packages
 *   - traveler: Can create trips
 *   - both: Can send packages and create trips
 *   - admin: Has administrative privileges (manage users, trips, packages, bookings)
 */

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;      // Firebase UID

    private String name;
    private String email;
    private String phone;

    private String role;    // sender | traveler | both | admin
}


