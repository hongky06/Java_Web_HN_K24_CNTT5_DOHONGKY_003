package org.example.java_web_hn_k24_cntt5_dohongky.model;

import jakarta.validation.constraints.*;

public class Course {
        private Long id;

        @NotBlank(message = "Tên khóa học không được để trống")
        @Size(min = 5, max = 100, message = "Tên phải từ 5-100 ký tự")
        private String courseName;

        @NotBlank(message = "Tên giảng viên không được để trống")
        private String instructor;

        @NotNull(message = "Thời lượng không được để trống")
        @Min(value = 1, message = "Thời lượng phải tối thiểu 1 giờ")
        @Max(value = 500, message = "Thời lượng không vượt quá 500 giờ")
        private Integer duration;

        private String thumbnail;

        public Course() {}

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        public String getInstructor() { return instructor; }
        public void setInstructor(String instructor) { this.instructor = instructor; }
        public Integer getDuration() { return duration; }
        public void setDuration(Integer duration) { this.duration = duration; }
        public String getThumbnail() { return thumbnail; }
        public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
}