package com.xiaoaxiao.graduatemanagementsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xiaoaxiao.graduatemanagementsystem.dao.*
import com.xiaoaxiao.graduatemanagementsystem.entity.*
import kotlin.concurrent.thread

@Database(version = 5,entities = [College::class,Job::class,StudentFavoriteJob::class,StudentDeliveredJob::class,StudentOfferedJob::class,Profession::class,Province::class,Student::class,Teacher::class,User::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun collegeDao(): CollegeDao
    abstract fun jobDao(): JobDao
    abstract fun professionDao(): ProfessionDao
    abstract fun provinceDao(): ProvinceDao
    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao
    abstract fun userDao(): UserDao
    abstract fun studentFavoriteDao(): StudentFavoriteJobDao
    abstract fun studentDeliveredDao(): StudentDeliveredJobDao
    abstract fun studentOfferedDao(): StudentOfferedJobDao

    companion object {
        private var instance:AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                val appDatabase = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                initData(appDatabase)
                instance = appDatabase

            }
            return instance!!
        }

        private fun initData(appDatabase: AppDatabase) {
            val studentDao = appDatabase.studentDao()
            val teacherDao = appDatabase.teacherDao()
            val collegeDao = appDatabase.collegeDao()
            val professionDao = appDatabase.professionDao()
            val provinceDao = appDatabase.provinceDao()
            val jobDao = appDatabase.jobDao()
            val studentOfferedJobDao = appDatabase.studentOfferedDao()

            val provinceList = mutableListOf<Province>(
                Province(provinceName = "??????"),
                Province(provinceName = "??????"),
                Province(provinceName = "??????"),
                Province(provinceName = "??????"),
                Province(provinceName = "??????"),
                Province(provinceName = "??????")
            )

            val collegeList = mutableListOf<College>(
                College(collegeName = "????????????"),
                College(collegeName = "????????????"),
                College(collegeName = "????????????")
            )

            val professionList = mutableListOf<Profession>(
                Profession(
                    professionName = "?????????",
                    collegeId = 1
                ),
                Profession(
                    professionName = "??????",
                    collegeId = 1
                ),
                Profession(
                    professionName = "??????",
                    collegeId = 1
                ),
                Profession(
                    professionName = "??????",
                    collegeId = 2
                ),
                Profession(
                    professionName = "??????",
                    collegeId = 3
                ),
                Profession(
                    professionName = "??????",
                    collegeId = 3
                )
            )

            val studentList = mutableListOf<Student>(
                // 0101??????
                Student(
                    studentName = "??????A",
                    sex = "???",
                    studentNumber = "2017010101",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 3
                ),
                Student(
                    studentName = "??????O",
                    sex = "???",
                    studentNumber = "2017010102",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 2
                ),
                Student(
                    studentName = "??????C",
                    sex = "???",
                    studentNumber = "2017010103",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 4
                ),
                Student(
                    studentName = "??????D",
                    sex = "???",
                    studentNumber = "2017010104",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 1
                ),
                Student(
                    studentName = "??????Y",
                    sex = "???",
                    studentNumber = "2017010105",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 1
                ),
                Student(
                    studentName = "??????U",
                    sex = "???",
                    studentNumber = "2017010106",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 1
                ),
                Student(
                    studentName = "??????G",
                    sex = "???",
                    studentNumber = "2017010107",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 6
                ),
                // 0102??????
                Student(
                    studentName = "??????F",
                    sex = "???",
                    studentNumber = "2017010201",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 1
                ),
                Student(
                    studentName = "??????I",
                    sex = "???",
                    studentNumber = "2017010202",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 4
                ),
                Student(
                    studentName = "??????N",
                    sex = "???",
                    studentNumber = "2017010203",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 5
                ),
                Student(
                    studentName = "??????K",
                    sex = "???",
                    studentNumber = "2017010204",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 1
                ),
                // 0103
                Student(
                    studentName = "??????L",
                    sex = "???",
                    studentNumber = "2017010301",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 5
                ),
                Student(
                    studentName = "??????M",
                    sex = "???",
                    studentNumber = "2017010302",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 4
                ),
                Student(
                    studentName = "??????J",
                    sex = "???",
                    studentNumber = "2017010303",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 6
                ),
                Student(
                    studentName = "??????B",
                    sex = "???",
                    studentNumber = "2017010304",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 1
                ),
                // 0204
                Student(
                    studentName = "??????P",
                    sex = "???",
                    studentNumber = "2017020401",
                    collegeId = 2,
                    professionId = 4,
                    provinceId = 1
                ),
                Student(
                    studentName = "??????Q",
                    sex = "???",
                    studentNumber = "2017020402",
                    collegeId = 2,
                    professionId = 4,
                    provinceId = 3
                ),
                Student(
                    studentName = "??????Z",
                    sex = "???",
                    studentNumber = "2017020403",
                    collegeId = 2,
                    professionId = 4,
                    provinceId = 2
                ),
                // 0305
                Student(
                    studentName = "??????S",
                    sex = "???",
                    studentNumber = "2017030501",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 6
                ),
                Student(
                    studentName = "??????T",
                    sex = "???",
                    studentNumber = "2017030502",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 1
                ),
                Student(
                    studentName = "??????H",
                    sex = "???",
                    studentNumber = "2017030503",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 1
                ),
                Student(
                    studentName = "??????V",
                    sex = "???",
                    studentNumber = "2017030504",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 3
                ),
                // 0306
                Student(
                    studentName = "??????W",
                    sex = "???",
                    studentNumber = "2017030601",
                    collegeId = 3,
                    professionId = 6,
                    provinceId = 2
                ),
                Student(
                    studentName = "??????R",
                    sex = "???",
                    studentNumber = "2017030602",
                    collegeId = 3,
                    professionId = 6,
                    provinceId = 4
                )
            )

            val teacherList = mutableListOf<Teacher>(
                Teacher(
                    teacherName = "??????A",
                    sex = "???",
                    teacherNumber = "11110101",
                    collegeId = 1,
                    professionId = 1
                ),
                Teacher(
                    teacherName = "??????B",
                    sex = "???",
                    teacherNumber = "11110102",
                    collegeId = 1,
                    professionId = 2
                ),
                Teacher(
                    teacherName = "??????C",
                    sex = "???",
                    teacherNumber = "11110103",
                    collegeId = 1,
                    professionId = 3
                ),
                Teacher(
                    teacherName = "??????D",
                    sex = "???",
                    teacherNumber = "11110204",
                    collegeId = 2,
                    professionId = 4
                ),
                Teacher(
                    teacherName = "??????E",
                    sex = "???",
                    teacherNumber = "11110305",
                    collegeId = 3,
                    professionId = 5
                ),
                Teacher(
                    teacherName = "??????F",
                    sex = "???",
                    teacherNumber = "11110306",
                    collegeId = 3,
                    professionId = 6
                )
            )

            val jobList = mutableListOf<Job>(
                Job(
                    jobName = "Java??????",
                    jobNature = "??????",
                    company = "??????",
                    salary = "10k/???",
                    provinceId = 2,
                    jobIntroduction = "Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????Java??????"
                ),
                Job(
                    jobName = "Python",
                    jobNature = "??????",
                    company = "??????",
                    salary = "12k/???",
                    provinceId = 3,
                    jobIntroduction = "Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????Python??????"
                ),
                Job(
                    jobName = "Go",
                    jobNature = "??????",
                    company = "??????",
                    salary = "13k/???",
                    provinceId = 1,
                    jobIntroduction = "Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????Go??????"
                ),
                Job(
                    jobName = "C++",
                    jobNature = "??????",
                    company = "??????",
                    salary = "8k/???",
                    provinceId = 6,
                    jobIntroduction = "C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????C++??????"
                )
            )

            val studentOfferedJobList = mutableListOf<StudentOfferedJob>(
                StudentOfferedJob(
                    studentId = 1,
                    jobId = 1
                ),
                StudentOfferedJob(
                    studentId = 2,
                    jobId = 3
                ),
                StudentOfferedJob(
                    studentId = 3,
                    jobId = 2
                ),
                StudentOfferedJob(
                    studentId = 5,
                    jobId = 1
                ),
                StudentOfferedJob(
                    studentId = 9,
                    jobId = 4
                ),
                StudentOfferedJob(
                    studentId = 14,
                    jobId = 2
                ),
                StudentOfferedJob(
                    studentId = 7,
                    jobId = 3
                ),
                StudentOfferedJob(
                    studentId = 13,
                    jobId = 1
                ),
                StudentOfferedJob(
                    studentId = 6,
                    jobId = 4
                ),
                StudentOfferedJob(
                    studentId = 8,
                    jobId = 2
                ),
                StudentOfferedJob(
                    studentId = 17,
                    jobId = 3
                ),
                StudentOfferedJob(
                    studentId = 16,
                    jobId = 4
                ),
                StudentOfferedJob(
                    studentId = 20,
                    jobId = 2
                ),
                StudentOfferedJob(
                    studentId = 23,
                    jobId = 1
                ),
                StudentOfferedJob(
                    studentId = 21,
                    jobId = 3
                ),
                StudentOfferedJob(
                    studentId = 24,
                    jobId = 2
                )
            )

            thread {
                provinceList.forEach {
                    if (provinceDao.findProvinceByName(it.provinceName).isEmpty()) {
                        provinceDao.insertProvince(it)
                    }
                }
                collegeList.forEach {
                    if (collegeDao.findCollegeByName(it.collegeName).isEmpty()) {
                        collegeDao.insertCollege(it)
                    }
                }
                professionList.forEach {
                    if (professionDao.findProfessionByName(it.professionName).isEmpty()) {
                        professionDao.insertProfession(it)
                    }
                }
                studentList.forEach {
                    if (studentDao.findStudentByStudentNumber(it.studentNumber).isEmpty()) {
                        studentDao.insertStudent(it)
                    }
                }
                teacherList.forEach {
                    if (teacherDao.findTeacherByTeacherNumber(it.teacherNumber).isEmpty()) {
                        teacherDao.insertTeacher(it)
                    }
                }
                jobList.forEach {
                    // TODO hongyangxiao ????????????????????????????????????????????????
                    if (jobDao.findJobByName(it.jobName).isEmpty()) {
                        jobDao.insertJob(it)
                    }
                }
                studentOfferedJobList.forEach {
                    if (studentOfferedJobDao.findStudentOfferedJobByStudentIdAndJobId(it.studentId,it.jobId).isEmpty()) {
                        studentOfferedJobDao.insertStudentOfferedJob(it)
                    }
                }
            }
        }
    }
}

