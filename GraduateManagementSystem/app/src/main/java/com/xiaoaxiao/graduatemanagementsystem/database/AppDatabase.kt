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
                Province(provinceName = "陕西"),
                Province(provinceName = "四川"),
                Province(provinceName = "北京"),
                Province(provinceName = "深圳"),
                Province(provinceName = "西藏"),
                Province(provinceName = "甘肃")
            )

            val collegeList = mutableListOf<College>(
                College(collegeName = "电智学院"),
                College(collegeName = "体育学院"),
                College(collegeName = "文理学院")
            )

            val professionList = mutableListOf<Profession>(
                Profession(
                    professionName = "计算机",
                    collegeId = 1
                ),
                Profession(
                    professionName = "网络",
                    collegeId = 1
                ),
                Profession(
                    professionName = "电信",
                    collegeId = 1
                ),
                Profession(
                    professionName = "足球",
                    collegeId = 2
                ),
                Profession(
                    professionName = "语文",
                    collegeId = 3
                ),
                Profession(
                    professionName = "数学",
                    collegeId = 3
                )
            )

            val studentList = mutableListOf<Student>(
                // 0101学生
                Student(
                    studentName = "学生A",
                    sex = "男",
                    studentNumber = "2017010101",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 3
                ),
                Student(
                    studentName = "学生O",
                    sex = "女",
                    studentNumber = "2017010102",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 2
                ),
                Student(
                    studentName = "学生C",
                    sex = "男",
                    studentNumber = "2017010103",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 4
                ),
                Student(
                    studentName = "学生D",
                    sex = "男",
                    studentNumber = "2017010104",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 1
                ),
                Student(
                    studentName = "学生Y",
                    sex = "男",
                    studentNumber = "2017010105",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 1
                ),
                Student(
                    studentName = "学生U",
                    sex = "女",
                    studentNumber = "2017010106",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 1
                ),
                Student(
                    studentName = "学生G",
                    sex = "男",
                    studentNumber = "2017010107",
                    collegeId = 1,
                    professionId = 1,
                    provinceId = 6
                ),
                // 0102学生
                Student(
                    studentName = "学生F",
                    sex = "女",
                    studentNumber = "2017010201",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 1
                ),
                Student(
                    studentName = "学生I",
                    sex = "男",
                    studentNumber = "2017010202",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 4
                ),
                Student(
                    studentName = "学生N",
                    sex = "女",
                    studentNumber = "2017010203",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 5
                ),
                Student(
                    studentName = "学生K",
                    sex = "男",
                    studentNumber = "2017010204",
                    collegeId = 1,
                    professionId = 2,
                    provinceId = 1
                ),
                // 0103
                Student(
                    studentName = "学生L",
                    sex = "女",
                    studentNumber = "2017010301",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 5
                ),
                Student(
                    studentName = "学生M",
                    sex = "女",
                    studentNumber = "2017010302",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 4
                ),
                Student(
                    studentName = "学生J",
                    sex = "男",
                    studentNumber = "2017010303",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 6
                ),
                Student(
                    studentName = "学生B",
                    sex = "男",
                    studentNumber = "2017010304",
                    collegeId = 1,
                    professionId = 3,
                    provinceId = 1
                ),
                // 0204
                Student(
                    studentName = "学生P",
                    sex = "男",
                    studentNumber = "2017020401",
                    collegeId = 2,
                    professionId = 4,
                    provinceId = 1
                ),
                Student(
                    studentName = "学生Q",
                    sex = "女",
                    studentNumber = "2017020402",
                    collegeId = 2,
                    professionId = 4,
                    provinceId = 3
                ),
                Student(
                    studentName = "学生Z",
                    sex = "女",
                    studentNumber = "2017020403",
                    collegeId = 2,
                    professionId = 4,
                    provinceId = 2
                ),
                // 0305
                Student(
                    studentName = "学生S",
                    sex = "男",
                    studentNumber = "2017030501",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 6
                ),
                Student(
                    studentName = "学生T",
                    sex = "女",
                    studentNumber = "2017030502",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 1
                ),
                Student(
                    studentName = "学生H",
                    sex = "男",
                    studentNumber = "2017030503",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 1
                ),
                Student(
                    studentName = "学生V",
                    sex = "男",
                    studentNumber = "2017030504",
                    collegeId = 3,
                    professionId = 5,
                    provinceId = 3
                ),
                // 0306
                Student(
                    studentName = "学生W",
                    sex = "女",
                    studentNumber = "2017030601",
                    collegeId = 3,
                    professionId = 6,
                    provinceId = 2
                ),
                Student(
                    studentName = "学生R",
                    sex = "男",
                    studentNumber = "2017030602",
                    collegeId = 3,
                    professionId = 6,
                    provinceId = 4
                )
            )

            val teacherList = mutableListOf<Teacher>(
                Teacher(
                    teacherName = "教师A",
                    sex = "女",
                    teacherNumber = "11110101",
                    collegeId = 1,
                    professionId = 1
                ),
                Teacher(
                    teacherName = "教师B",
                    sex = "男",
                    teacherNumber = "11110102",
                    collegeId = 1,
                    professionId = 2
                ),
                Teacher(
                    teacherName = "教师C",
                    sex = "男",
                    teacherNumber = "11110103",
                    collegeId = 1,
                    professionId = 3
                ),
                Teacher(
                    teacherName = "教师D",
                    sex = "女",
                    teacherNumber = "11110204",
                    collegeId = 2,
                    professionId = 4
                ),
                Teacher(
                    teacherName = "教师E",
                    sex = "女",
                    teacherNumber = "11110305",
                    collegeId = 3,
                    professionId = 5
                ),
                Teacher(
                    teacherName = "教师F",
                    sex = "女",
                    teacherNumber = "11110306",
                    collegeId = 3,
                    professionId = 6
                )
            )

            val jobList = mutableListOf<Job>(
                Job(
                    jobName = "Java开发",
                    jobNature = "运营",
                    company = "字节",
                    salary = "10k/月",
                    provinceId = 2,
                    jobIntroduction = "Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发Java开发"
                ),
                Job(
                    jobName = "Python",
                    jobNature = "运营",
                    company = "腾讯",
                    salary = "12k/月",
                    provinceId = 3,
                    jobIntroduction = "Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发Python开发"
                ),
                Job(
                    jobName = "Go",
                    jobNature = "产品",
                    company = "小米",
                    salary = "13k/月",
                    provinceId = 1,
                    jobIntroduction = "Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发Go开发"
                ),
                Job(
                    jobName = "C++",
                    jobNature = "技术",
                    company = "阿里",
                    salary = "8k/月",
                    provinceId = 6,
                    jobIntroduction = "C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发C++开发"
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
                    // TODO hongyangxiao 这样肯定是不行的，按照名字在筛选
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

