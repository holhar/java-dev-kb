package de.holhar.java_dev_kb.training.pcps.ch01_container.s0120_s0121_s0122_s0123_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// Q1.22
@Profile("prod")
@Component
public class ProfileComponent {
}
