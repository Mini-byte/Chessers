#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec2 texCoord;

out vec3 Position;
out vec3 Normal;
out vec2 TexCoord;

uniform mat4 transform;
uniform mat4 projectedTransform;

void main()
{
    gl_Position = projectedTransform * vec4(position, 1.0f);
    Position = vec3(transform * vec4(position, 1.0f));
    Normal = mat3(transpose(inverse(transform))) * normal;
    TexCoord = vec2(texCoord.x, 1.0 - texCoord.y);
}